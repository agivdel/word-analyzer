package agivdel.wordanalyzer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import agivdel.wordanalyzer.model.WordAnalyzer;

import java.util.Map;

@Controller
public class WordAnalyzerController {
    @GetMapping("/analyze")
    public String analyzeWord(
            @RequestParam(name = "word", defaultValue = "test") String word,
            Map<String, Object> model) {
        model.put("word", word);
        Map<Character, Integer> result = new WordAnalyzer().getMostCommonLetter(word);
        for (var entry : result.entrySet()) {
            model.put("letter", entry.getKey());
            model.put("count", entry.getValue());
        }
        return "word";
    }
}