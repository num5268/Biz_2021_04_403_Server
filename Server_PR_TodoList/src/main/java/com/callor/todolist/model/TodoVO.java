package com.callor.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
	
	private Long td_seq; // NUMBER PRIMARY KEY,
	
    private String td_work; // NVARCHAR2(300) NOT NULL,
    private String td_date; // VARCHAR2(15) NOT NULL,
    private String td_time; // NVARCHAR2(300) NOT NULL,
    private String td_place; // VARCHAR2(300) NOT NULL

}
