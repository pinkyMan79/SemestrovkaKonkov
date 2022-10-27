package com.miusicmaker.musmk;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ButtonsNoteSheetServlet extends HttpServlet {

    List<String> notes  = new ArrayList<>();
    int octaveNum;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String[] noteName = {"B", "A#", "A", "G#", "G", "F#", "F", "E", "D#", "D", "C#", "C" };

        if (req.getParameter("octave") == null){

            octaveNum = 1;

        }else {

            octaveNum = Integer.parseInt(req.getParameter("octave"));

        }

        req.setAttribute("octave", octaveNum);

        //notes.add(s + octaveNum);

        for (String s : noteName) {

            if (req.getParameter(s + octaveNum) != null) {

                notes.add(s);

            }

        }

        HttpSession session = req.getSession();

        // очень извиняюсь, но бля, я не знаю как это испрaвить
        session.setAttribute("r_notes", notes);

        req.setAttribute("readyNotes", notes);

        req.getRequestDispatcher("/mus_sheet.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //doGet(req,resp);

    }

}
