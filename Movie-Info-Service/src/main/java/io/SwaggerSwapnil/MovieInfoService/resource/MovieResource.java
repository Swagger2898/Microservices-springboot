package io.SwaggerSwapnil.MovieInfoService.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.SwaggerSwapnil.MovieInfoService.Model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId , "Titanic");
	}

}
