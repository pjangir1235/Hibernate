package com.hibernate.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.model.Order;

@RestController("order")
@RequestMapping("/order")
@Transactional
public class OrderCont {
    @Autowired
    SessionFactory sessionFact;

    @GetMapping("/")
    public List<Order> getOrders() {
	return sessionFact.getCurrentSession().createQuery("from Order").list();
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveOrder(@RequestBody Order order) {
	try {
	    sessionFact.getCurrentSession().save(order);
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	} catch (Exception e) {
	    System.out.println(e);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
    }

}
