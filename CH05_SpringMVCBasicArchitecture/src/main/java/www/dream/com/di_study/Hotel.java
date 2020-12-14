package www.dream.com.di_study;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Hotel {
	@Getter
	@NonNull
	private Chef chef;
	
	@Getter
	private Restaurant restaurant;
}
