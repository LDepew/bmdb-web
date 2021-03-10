package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.MovieCollection;
import com.bmdb.business.User;
import com.bmdb.db.MovieCollectionRepo;
import com.bmdb.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/movie-collections")
public class MovieCollectionController {
	
	@Autowired
	private MovieCollectionRepo movieCollectionRepo;

	@Autowired
	private UserRepo userRepo;

	@GetMapping("/")
	public List<MovieCollection> getAllUsers() {
		return movieCollectionRepo.findAll();
	}

	@GetMapping("/{id}")
	public MovieCollection getById(@PathVariable int id) {
		return movieCollectionRepo.findById(id).get();
	}
	
	@PostMapping("/")
	public MovieCollection addMovieCollection(@RequestBody MovieCollection movieCollection) {
		//save mc
		movieCollectionRepo.save(movieCollection);
		//recalculate collection value
		recalculateCollectionValue(movieCollection);
		return movieCollectionRepo.save(movieCollection);
	}
	
	private void recalculateCollectionValue(MovieCollection movieCollection) {
		User u = movieCollection.getUser();
		//get all mc's for this user
		List<MovieCollection> movieCollections = 
				movieCollectionRepo.findAllByUserId(u.getId());
		//declare newTotal = 0
		double newTotal = 0.0;
		//loop thru mcs
		for (MovieCollection movieCollect:movieCollections) {
			//add purchase price to newTotal
			newTotal += movieCollection.getPurchasePrice();
		}
		
		//set newTotal in user
		u.setCollectionValue(newTotal);
		//save user
		userRepo.save(u);
		
	}
	
	
	@PutMapping("/") 
	public MovieCollection updateMovieCollection(@RequestBody MovieCollection movieCollection) {
		return movieCollectionRepo.save(movieCollection);
	}
	
	@DeleteMapping("/id") 
	public MovieCollection delete(@PathVariable int id) {
		Optional<MovieCollection> movieCollection = movieCollectionRepo.findById(id);
		if (movieCollection.isPresent()) {
			movieCollectionRepo.delete(movieCollection.get());
		}
		else {
			System.out.println("Delete Error - movieCollection not found for id: "+id);
		}
		return movieCollection.get();
	}
	
	

}
