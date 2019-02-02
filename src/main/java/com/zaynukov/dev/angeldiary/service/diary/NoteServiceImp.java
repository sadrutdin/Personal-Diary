package com.zaynukov.dev.angeldiary.service.diary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.zaynukov.dev.angeldiary.exception.DiaryNotFoundFile;
import com.zaynukov.dev.angeldiary.exception.DiaryUnknownException;
import com.zaynukov.dev.angeldiary.obj.Diary;
import com.zaynukov.dev.angeldiary.obj.Note;
import com.zaynukov.dev.angeldiary.service.cipher.MyCipher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;

@Service
class NoteServiceImp implements NoteService {

    private DateTimeFormatter formatter;

    private boolean existDiary;

    public boolean isExistDiary() {
        return existDiary;
    }

    @Inject
    public NoteServiceImp(MyCipher cipher) {
        this.cipher = cipher;
    }

    private MyCipher cipher;
    private Gson gson;

    @PostConstruct
    private void init() {
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create();

        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            String j = readFileToString(new ClassPathResource("j").getFile(), UTF_8);
            existDiary = true;
        } catch (IOException e) {
            e.printStackTrace();
            existDiary = false;
        }

    }

    @Override
    public List<LocalDateTime> datesOfWriteNotes(String password) throws DiaryNotFoundFile, DiaryUnknownException {
        List<Note> noteList = getNote(password);
        List<LocalDateTime> res = new ArrayList<>(noteList.size());
        Iterator<Note> it = noteList.iterator();
        //noinspection WhileLoopReplaceableByForEach
        while (it.hasNext()) {
            res.add(it.next().getDateTime());
        }
        return res;
    }

    @Override
    public List<LocalDateTime> datesOfWriteNotes(String password, String ddMMyyyy_A, String ddMMyyyy_B)
            throws DiaryNotFoundFile, DiaryUnknownException {
        List<Note> noteList = getNote(password);
        LocalDateTime date1 = LocalDateTime.parse(ddMMyyyy_A, formatter);
        LocalDateTime date2 = LocalDateTime.parse(ddMMyyyy_B, formatter);

        List<LocalDateTime> res = new ArrayList<>();
        Iterator<Note> it = noteList.iterator();
        //noinspection WhileLoopReplaceableByForEach
        while (it.hasNext()) {
            Note obj = it.next();
            LocalDateTime dt = obj.getDateTime();
            if (dt.isAfter(date1) && dt.isBefore(date2)) {
                res.add(dt);
            }
        }
        return res;
    }

    private List<Note> getNote(String password) throws JsonSyntaxException, DiaryUnknownException, DiaryNotFoundFile {
        String j;
        try {
            j = cipher.decrypt(password, readFileToString(new ClassPathResource("j").getFile(), UTF_8));
        } catch (BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new DiaryUnknownException("Ошибка при работе с сервисом шифрования", e);
        } catch (IOException e) {
            throw new DiaryNotFoundFile("Не найден файл дневника", e);
        }

        Diary d = new Gson().fromJson(j, Diary.class);
        return new LinkedList<>(d.getDiary());
    }

}
