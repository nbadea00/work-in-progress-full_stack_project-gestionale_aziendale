package com.backend.api.user.skill;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.api.user.exception.MyAPIException;

@Service
public class SkillService {

	@Autowired SkillRepository repo;
	
	public Skill save(Skill args) {
		return repo.save(args);
	}
	
	public Skill update(Skill args) {
		return repo.save(args);
	}
	
	public Skill findById(Long id) {
		return repo.findById(id).orElseThrow();
	}
	
	public String delById(Long id) {
		repo.deleteById(id);
		return "Skill remuved";
	}
	
	public List<Skill> findAll() {
		return repo.findAll();
	}
	
	public Set<Skill> gerListSkillFromListSkillName(Set<Skill> skillSet){
		return skillSet.stream().map((name) -> repo.findBySkillName(name.getSkillName()).orElseThrow(() -> new MyAPIException(HttpStatus.NOT_FOUND, "Skill not found"))).collect(Collectors.toSet());
	}
}
