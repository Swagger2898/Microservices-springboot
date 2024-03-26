package io.SwaggerSwapnil.RatingsdataService.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.SwaggerSwapnil.RatingsdataService.Model.Rating;
import io.SwaggerSwapnil.RatingsdataService.Model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping("/users/{userId}")
	public UserRating getRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",3),
				new Rating("2345",2)
				);
		 UserRating userRating= new UserRating();
		 userRating.setUserRating(ratings);
		 return userRating;
		 
	}
}
