package io.SwaggerSwapnil.MovieCatalogService.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.SwaggerSwapnil.MovieCatalogService.Model.CatalogItem;
import io.SwaggerSwapnil.MovieCatalogService.Model.Movie;
import io.SwaggerSwapnil.MovieCatalogService.Model.Rating;

@RestController
@RequestMapping("/catalog2")
public class MovieCatalogResource2 {
	
	@Autowired
  //reactive web dependency 
	private WebClient.Builder webClientBuilder;

	
	@RequestMapping("/{userId}")
	public List<CatalogItem>getCatalog(@PathVariable("userId") String userId){
		
		List<Rating>  ratings = Arrays.asList(
				new Rating("1232",3),
				new Rating("7654",5)
				);
		
		
		return ratings.stream().map(rating ->{
			
		Movie movie=	webClientBuilder.build()
			.get()
			.uri("http://localhost:8081/movie/\"+ rating.getMovieId()")
			.retrieve()
			.bodyToMono(Movie.class)
			.block();

		return new CatalogItem(movie.getName(),"romance",rating.getRating());
		})
				.collect((Collectors.toList()));
	
	

		

		
	}
	
	
	


}
