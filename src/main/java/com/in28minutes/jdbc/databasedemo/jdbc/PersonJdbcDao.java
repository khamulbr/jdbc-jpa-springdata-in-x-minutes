package com.in28minutes.jdbc.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in28minutes.jdbc.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
		
	// dont really need that, it´s just to know how it is when´s needed
	class PersonRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}		
	}
	
	public List<Person> findAll(){
		return jdbcTemplate.query("select * from person", 
				new PersonRowMapper());
	}
	
	public Person findById(int id){
		return (Person) jdbcTemplate.queryForObject("select * from person where id = ?", new Object[] {id},
				new PersonRowMapper());
	}
	
	public List<Person> findByName(String name){
		return jdbcTemplate.query("select * from person where name = ?", new Object[] {name},
				new PersonRowMapper());
	}
	
	public List<Person> findByLocation(String location){
		return jdbcTemplate.query("select * from person where location = ?", new Object[] {location},
				new PersonRowMapper());
	}
	
	public int deleteById(int id){
		return jdbcTemplate.update("delete from person where id = ?", new Object[] {id});
	}
	
	public int insert(Person person){
		return jdbcTemplate.update(
				"insert into PERSON (ID, NAME, LOCATION, BIRTH_DATE) " + 
				"values (?, ?, ?, ?); ", 
				new Object[] {
						person.getId(), person.getName(), 
						person.getLocation(), new Timestamp(person.getBirthDate().getTime())});
	}
	
	public int update(Person person){
		return jdbcTemplate.update(
				"update PERSON set NAME = ? , LOCATION = ?, BIRTH_DATE = ? " +
				"where id = ? ", 
				new Object[] {
						person.getName(), person.getLocation(), 
						new Timestamp(person.getBirthDate().getTime()), person.getId()});
	}

}
