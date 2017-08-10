package com.ironmovies.controllers;

import com.ironmovies.models.Movie;
import com.ironmovies.models.ResultsPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
public class MovieController {
    List<Movie> movies = new ArrayList();
    static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";
    static final String POP_API_URL = "https://api.themoviedb.org/3/movie/popular?api_key=be2a38521a7859c95e2d73c48786e4bb";;

    @GetMapping(path ="/api/test")
    public Object singleMovieTest(String route) {
        RestTemplate testTemplate = new RestTemplate();
        ResultsPage resultsPage = testTemplate.getForObject(API_URL, ResultsPage.class);
        return resultsPage.getResults().stream()
                .filter(e->e.getTitle()
                        .contains("Spider-Man: Homecoming"))
                        .collect(Collectors.toList());
    }

    @GetMapping(path = "/api/movies")
    public Object getMovies(String route) {
        //TODO: checkfor api key ="abc"
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject(API_URL, ResultsPage.class);
        return resultsPage;
    }
//        @PostMapping(path ="/api/movies/{id}")
//                public Object getMovie(String route){
//            RestTemplate restTemplate = new RestTemplate();
//            ResultsPage resultsPage = restTemplate.getForObject(API_URL, ResultsPage.class);
//            return resultsPage.getResults().stream().filter();
//        }
        @GetMapping(path = "/api/popular")
    public Object popularMovies(String route){
            RestTemplate restTemplate = new RestTemplate();
            ResultsPage resultsPage = restTemplate.getForObject(POP_API_URL, ResultsPage.class);
            return resultsPage.getResults();
        }

    }

