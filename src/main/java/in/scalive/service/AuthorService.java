package in.scalive.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.scalive.dto.AuthorUpdateDTO;
import in.scalive.entity.Author;
import in.scalive.exception.ResourceNotFoundException;
import in.scalive.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepo;
    
    // getting all authors
    public List<Author> getAllUsers() {
        return authorRepo.findAll();
    }
    
    // getting single author
    public Author getUserById(Long id) {
        Author author = authorRepo.findById(id).orElse(null);
        
        if(author == null) {
        	throw new ResourceNotFoundException("No author with Id: "+id+" found!");
        }
        
        return author;   
    }

    	// updating  author
    public Author updateUser(Long id, AuthorUpdateDTO updAuthor) {

        Author author = getUserById(id);
        
        if(updAuthor.getName() == null && updAuthor.getEmail() == null && updAuthor.getAbout() == null) {
        	throw new RuntimeException("Empty object not allowed!");
        }
        
        if(updAuthor.getName() != null && updAuthor.getName().isBlank()) {
        	throw new RuntimeException("Name cannot be blank!");
        }
        
        if(updAuthor.getAbout() != null && updAuthor.getAbout().isBlank()) {
        	throw new RuntimeException("About cannot be blank!");
        }

        // updating author details
        if(updAuthor.getName() != null) {
        	author.setName(updAuthor.getName());
        }
        
        if(updAuthor.getEmail() != null) {
        	author.setEmail(updAuthor.getEmail());
        }
        
        if(updAuthor.getAbout() != null) {
        	author.setAbout(updAuthor.getAbout());
        }
 
     return authorRepo.save(author);

       
    }

    	//deleting author 
    public void deleteuser(Long id) {
    	Author author = getUserById(id);
    	authorRepo.delete(author);
    	
        
    }
}