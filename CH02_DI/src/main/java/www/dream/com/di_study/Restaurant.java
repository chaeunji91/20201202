package www.dream.com.di_study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Getter;

@Repository
public class Restaurant {
	//DI
	@Autowired @Getter
	private Chef chef;	//Composite
}
