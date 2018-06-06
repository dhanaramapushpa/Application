package com.example.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Preference;
import com.example.demo.repository.PreferenceRepository;
import com.example.demo.service.PreferenceService;
import com.example.demo.util.Response;

@Service
public class PreferenceImpl implements PreferenceService{

	
@Autowired PreferenceRepository preferenceRepo;
	
	@Override
	public Response CreateSampleData() {
		try {
			Preference preference = new Preference();
			preference.setCategory("Cinema");
			preference.setStatus(Boolean.TRUE);
			preference.setTypeOfPref("Action");
			
			Preference preference1 = new Preference();
			preference1.setCategory("Cinema");
			preference1.setTypeOfPref("Drama");
			
			Preference preference2 = new Preference();
			preference2.setCategory("Games");
			preference2.setTypeOfPref("Cricket");
			
			Preference preference3 = new Preference();
			preference3.setCategory("Games");
			preference3.setTypeOfPref("Chess");
			
			preferenceRepo.save(preference);
			preferenceRepo.save(preference1);
			preferenceRepo.save(preference2);
			preferenceRepo.save(preference3);
			return new Response("saved",200, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response();
		}
	}
}
