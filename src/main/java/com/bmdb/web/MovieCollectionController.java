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

	@GetMapping("/")
	public List<MovieCollection> getAllUsers() {
		return movieCollectionRepo.findAll();
	}

	@GetMapping("/{id}")
	public MovieCollection getById(@PathVariable int id) {
		return movieCollectionRepo.findById(id).get();
	}
	
	@PostMapping("/") 
	public MovieCollection create(@RequestBody MovieCollection movieCollection) {
		return movieCollectionRepo.save(movieCollection);
	}
	
	@PostMapping("/")
	public MovieCollection addMovieCollection(@RequestBody MovieCollection movieCollection) {
		//save mc
		movieCollectionRepo.save(movieCollection);
		//get all mc's for this user
		List<MovieCollection> movieCollections = 
				movieCollectionRepo.findAllByUserId(movieCollection.getUser().getId());
		//declare newTotal = 0
		//loop thru mcs
		//add purchase price to newTotal
		//set newTotal in user
		//save user
		return movieCollectionRepo.save(movieCollection);
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
