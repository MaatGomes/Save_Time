package com.maats.saveseries.controller;

import java.util.List;

import javax.validation.Valid;

import com.maats.saveseries.model.Filme;
import com.maats.saveseries.model.Serie;
import com.maats.saveseries.repository.FilmeRepository;
import com.maats.saveseries.repository.SerieRepository;
import com.maats.saveseries.service.FilmeService;
import com.maats.saveseries.service.SerieService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class SeriesController {
    
    @Autowired
    SerieService serieService;
  

    @Autowired
    SerieRepository serieRepository;
  
    @Autowired
    FilmeService filmeService;

    @Autowired
    FilmeRepository filmeRepository;


    @GetMapping("/series")
    public String getSeries(Serie s, Model model){
        List<Serie> series = serieService.findAll();
        model.addAttribute("series", series);
        model.addAttribute("s", s);

        return "series";
    }
    

    @PostMapping("/series")
    public String saveCardSerie(@Valid Serie serie, BindingResult result, RedirectAttributes attributes){
        
        if (result.hasErrors()) {
         
            attributes.addFlashAttribute("serie", serie);
            return "redirect:/series";

        }
        serieRepository.save(serie);

        return "redirect:/series";

    }

    @GetMapping("/series")
    public String getFilmes(Filme f, Model model){
        List<Filme> filmes = filmeService.findAll();
        model.addAttribute("filmes", filmes);
        model.addAttribute("f", f);
        return "filmes";
    }

    @PostMapping("/series")
    public String saveCardFilme(@Valid Filme filme, BindingResult result, RedirectAttributes attributes){
        
        if (result.hasErrors()) {
         
            attributes.addFlashAttribute("filme", filme);
            return "redirect:/series";

        }
        filmeRepository.save(filme);

        return "redirect:/series";

    }


    

    @GetMapping("/cardDetails/{id}")
    public String getCardDetailsSerie(@PathVariable("id") Long id) {

        serieService.findById(id);

        return "series";
    }

    @GetMapping("/cardDetails/{id}")
    public String getCardDetailsFilme(@PathVariable("id") Long id) {

        filmeService.findById(id);

        return "filmes";
    }
  


}