package com.india;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.india.model.AuthenticationRequest;
import com.india.model.AuthenticationResponse;
import com.india.model.Book;
import com.india.service.MyUserDetailsService;
import com.india.util.JwtUtil;


@RestController
public class Controller {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	


	@RequestMapping( value= "/" ,method = RequestMethod.GET)
	public Map<String, String> firstPage(Model map) {
		Map<String, String> books = new HashMap<String, String>();
		books.put("Big Bang", "Sam");
		books.put("India", "Akshay");
		books.put("Best IT", "Ratan TATA");
    	map.addAttribute("books", books);
        return (books);
	}
	
	@RequestMapping(value = "/welcom", method = RequestMethod.POST)
	public Book welcom(@RequestBody Book book) {
		
		return book;
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public Map<String, String> hello(Model map) {
		Map<String, String> books = new HashMap<String, String>();
		books.put("GeeKs&Geeks", "R C Gupta");
		books.put("JAVA", "Oracle");
		books.put("TCS", "Ratan TATA");
		books.put("Monogo", "Atlans");
    	map.addAttribute("books", books);
        return (books);
	}
	 @GetMapping("/user")
	    public String user() {
	        return ("<h1>Welcome User</h1>");
	    }

	
	    @RequestMapping(value = "/helloAdmin", method = RequestMethod.GET)
		public Map<String, String> helloAdmin(Model map) {
			Map<String, String> books = new HashMap<String, String>();
			books.put("Hello Admin", "admin");
			books.put("JWT security ", "JPA");
			books.put("Hibernate", "Red Hat");
	    	map.addAttribute("books", books);
	        return (books);
		}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	

}
