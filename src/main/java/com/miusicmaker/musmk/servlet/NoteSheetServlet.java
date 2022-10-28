package com.miusicmaker.musmk.servlet;

import com.miusicmaker.musmk.jdbc.SimpleDataSource;
import com.miusicmaker.musmk.model.MusicFile;
import com.miusicmaker.musmk.model.User;
import com.miusicmaker.musmk.repositories.MusicFileRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import jakarta.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class NoteSheetServlet extends HttpServlet {

    private MusicFileRepositoryImpl repository;

    @Override
    public void init() throws ServletException {

        Properties properties = TestConnection.getProperties();
        DataSource dataSource = new SimpleDataSource(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

        repository = new MusicFileRepositoryImpl(dataSource);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        List<String> notes = (List<String>) session.getAttribute("r_notes");

        StringBuilder nt_str = new StringBuilder();

        log(notes.toString());

        for (int i = 0; i < notes.size(); i++) {

            nt_str.append(notes.get(i)).append(" ");

        }

        Pattern voice = new Pattern();

        User u = (User) session.getAttribute("user");

        Player player = new Player();

        String del = " ";

        String values = String.join(del, notes);

        UUID mUUID = UUID.randomUUID();

        voice.add(values);

        voice.setTempo(Integer.parseInt( req.getParameter("tempo")));

        voice.setInstrument(req.getParameter("instrument"));

        File file = new File("/home/terenin/Downloads/glassfish6/glassfish/domains/domain1/" + mUUID + ".mid");

        MidiFileManager.savePatternToMidi(voice, file);

        req.setAttribute("music", file);

        //player.play("A1 B1 C1 D1 E1 F1 G1");
        player.play(voice);

        repository.save(MusicFile.builder().name(mUUID.toString()).path(file.getPath()).uId(u.getId()).build());

        //session.setAttribute("n_s", nt_str.toString());

        req.getRequestDispatcher("/get_notes").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
