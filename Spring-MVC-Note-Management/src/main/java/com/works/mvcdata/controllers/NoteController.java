package com.works.mvcdata.controllers;

import com.works.mvcdata.props.Note;
import com.works.mvcdata.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    NoteService noteservice = new NoteService();
    int status = -1;
    String message = "";
    int id = 0;


    @GetMapping("/note")
    public String note(Model model, @RequestParam(defaultValue = "1") int p) {
        model.addAttribute("note", noteservice.note(p));

        model.addAttribute("message", message);
        model.addAttribute("status", status);
        model.addAttribute("id", id);
        model.addAttribute("p", p);
        int count = noteservice.totalCount();
        model.addAttribute("count", count);
        int page = count % 10 == 0 ? count / 4 : (count / 4) + 1;
        model.addAttribute("page", page);

        return "note";

    }

    @GetMapping("/noteDelete/{id}")
    public String noteDelete(@PathVariable int id) {
        status = noteservice.deleteNote(id);
        if (status > 0) {
            message = "Delete Success - " + id;
            this.id = id;

        } else {
            message = "Delete Fail - " + id;
        }
        return "redirect:/note";

    }

    @GetMapping("/noteUndo/{id}")
    public String noteUndo(@PathVariable int id) {
        noteservice.deleteNote(id);
        return "redirect:/note";

    }

    @PostMapping("/noteSave")
    public String noteSave(Note note) {
        int status = noteservice.noteSave(note);
        if (status > 0)
            return "redirect:/";
        return "note";

    }

    @PostMapping("/noteUpdate")
    public String noteUpdate(Note note) {
        noteservice.noteUpdate(note);
        return "redirect:/note";
    }
}
