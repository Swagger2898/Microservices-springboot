package io.SwaggerSwapnil.MovieCatalogService.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.SwaggerSwapnil.MovieCatalogService.Model.CatalogItem;
import io.SwaggerSwapnil.MovieCatalogService.Model.Movie;
import io.SwaggerSwapnil.MovieCatalogService.Model.Rating;
import io.SwaggerSwapnil.MovieCatalogService.Model.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	private RestTemplate  restTemplate ;

	
	@RequestMapping("/{userId}")
	@CircuitBreaker(name= "random", fallbackMethod = "fallback")
	public List<CatalogItem>getCatalog(@PathVariable("userId") String userId){
		
		UserRating ratings = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratingsdata/users/"+userId, UserRating.class) ;
		
		
		return ratings.getUserRating().stream().map(rating ->{
		Movie movie =	restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/"+ rating.getMovieId(), Movie.class);

		return new CatalogItem(movie.getName(),"romance",rating.getRating());
		})
				.collect((Collectors.toList()));
	
	

		
	}
	
	public List<CatalogItem> fallback(Throwable t){
		UserRating ratings=new UserRating();
		List<Rating>  rating = Arrays.asList(
				new Rating("1232",3),
				new Rating("7654",9)
				);
		ratings.setUserRating(rating);

		
		
			
			return ratings.getUserRating().stream().map(ratin ->{
				Movie movie =	restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/"+ ratin.getMovieId(), Movie.class);

				return new CatalogItem(movie.getName(),"romance",ratin.getRating());
				})
						.collect((Collectors.toList()));
	

		

		
		}
	
	
	
	

		 
	
	


}