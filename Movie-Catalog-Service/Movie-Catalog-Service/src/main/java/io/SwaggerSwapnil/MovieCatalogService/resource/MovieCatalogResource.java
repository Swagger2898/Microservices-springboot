package io.SwaggerSwapnil.MovieCatalogService.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.SwaggerSwapnil.MovieCatalogService.Model.CatalogItem;
import io.SwaggerSwapnil.MovieCatalogService.Model.Movie;
import io.SwaggerSwapnil.MovieCatalogService.Model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	private RestTemplate  restTemplate ;

	
	@RequestMapping("/{userId}")
	public List<CatalogItem>getCatalog(@PathVariable("userId") String userId){
		
		UserRating ratings = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratingsdata/users/"+userId, UserRating.class) ;
		
		
		return ratings.getUserRating().stream().map(rating ->{
		Movie movie =	restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/"+ rating.getMovieId(), Movie.class);

		return new CatalogItem(movie.getName(),"romance",rating.getRating());
		})
				.collect((Collectors.toList()));
	
	

		

		
	}
	
	
	
}
		 
	
	


