package lt.itakademija.candidateCRUD;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository repository;

	@Transactional
	public CandidateEntity save(CandidateEntity c) {
		return repository.save(c);
	}

	@Transactional(readOnly = true)
	public List<CandidateEntity> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public CandidateEntity findCandidate(String personCode) {
		return repository.findCandidate(personCode);
	}

	@Transactional
	public CandidateEntity delete(Long id) {
		return repository.delete(id);
	}

	@Transactional
	public List<CandidateEntity> candidateListByCountyId(Long countyId) {
		return repository.candidateListByCountyId(countyId);
	}

	@Transactional
	public List<CandidateEntity> candidateListForSingleMemberResultByCountyId(Long countyId) {
		return repository.candidateListForSingleMemberResultByCountyId(countyId);
	}

	// Trina visus kandidatus kurie priklauso tam tikrai partijai(Partijos
	// kandidatu saraso trinimas)
	@Transactional
	public int deleteCandidatesByPartyId(int id) {
		return repository.deleteCandidatesByPartyId(id);
	}

	@Transactional
	public int deleteCandidatesByCountyId(Long countyId) {
		return repository.deleteCandidatesByCountyId(countyId);
	}

	@Transactional
	public void saveMultiPartyListFromCSV(MultipartFile CSVfile, int partyId) {
		List<CandidateEntity> list = new ArrayList<>();
		File file = new File(CSVfile.getOriginalFilename());
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(CSVfile.getBytes());
			fos.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		String data = "";
		String[] values = {};
		String firsName = "";
		String lastName = "";
		String dateString = "";
		String personCode = "";
		String description = "";
		Long countyId = 0L;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			while ((data = br.readLine()) != null) {

				values = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				firsName = values[0];
				lastName = values[1];
				dateString = values[2];
				personCode = values[3];
				description = values[4];

				list.add(new CandidateEntity(personCode, firsName, lastName, dateString, description, countyId,
						partyId));

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		for (CandidateEntity c : list) {
			if (repository.findCandidate(c.getPersonCode()) != null) {
				throw new IllegalArgumentException("candidate already in the list.");
			}
		}

		for (CandidateEntity c : list) {
			repository.save(c);
		}
	}

	@Transactional
	public void saveSinglePartyListFromCSV(MultipartFile CSVfile, Long countyId) {

		List<CandidateEntity> list = new ArrayList<>();

		File file = new File(CSVfile.getOriginalFilename());
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(CSVfile.getBytes());
			fos.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		String data = "";
		String[] values = {};
		String firsName = "";
		String lastName = "";
		String dateString = "";
		String personCode = "";
		String description = "";
		Integer partyId = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			while ((data = br.readLine()) != null) {

				values = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				firsName = values[0];
				lastName = values[1];
				dateString = values[2];
				personCode = values[3];
				description = values[4];
				partyId = Integer.parseInt(values[5]);

				list.add(new CandidateEntity(personCode, firsName, lastName, dateString, description, countyId,
						partyId));

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		for (CandidateEntity c : list) {

			if (repository.findCandidate(c.getPersonCode()) != null) {

				throw new IllegalArgumentException("candidate already in the list.");
			}

		}

		for (CandidateEntity c : list) {
			repository.save(c);
		}

	}

}
