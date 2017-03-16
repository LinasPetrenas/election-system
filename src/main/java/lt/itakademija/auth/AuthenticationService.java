package lt.itakademija.auth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import lt.itakademija.candidateCRUD.CandidateEntity;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationRepository repository;
	private BCryptPasswordEncoder passwordEncoder;
	@Transactional
	public AuthenticationEntity save(AuthenticationEntity a) {
		String password = a.getPassword();
		passwordEncoder = new BCryptPasswordEncoder(5);
		String bcryptPassword = passwordEncoder.encode(password);

		a.setPassword(bcryptPassword);

		return repository.save(a);
	}

	@Transactional(readOnly = true)
	public AuthenticationEntity findUser(Long id) {
		return repository.findUser(id);
	}
	
	@Transactional(readOnly = true)
	public AuthenticationEntity findUserByNameAndPass(AuthenticationEntity a) {
		List<AuthenticationEntity> list = new ArrayList<>(findAll());

		for(AuthenticationEntity auth : list){
			if(passwordEncoder.matches(a.getPassword(), auth.getPassword())){
				a.setPassword(auth.getPassword());
			}	
		}
		
		return repository.findUserByNameAndPass(a);
	}
	
	
	@Transactional(readOnly = true)
	public List<AuthenticationEntity> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public int delete( Long id) {
		return repository.delete(id);
	}
	
}
