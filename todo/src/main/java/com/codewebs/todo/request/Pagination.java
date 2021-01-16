package com.codewebs.todo.request;



import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
	
	
	
	private Integer pageNumber;

	private Integer size;

	public Pageable toPageable() {
		return PageRequest.of(pageNumber, size);
	}

}
