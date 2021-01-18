//package com.codewebs.todo.entities;
//
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "role")
//public class Role  {
//	
//	public static final Object ADMIN = null;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	String name;
//	@ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//}


