package com.bam.GESTIBANKBAM.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bam.GESTIBANKBAM.model.FileModel;
import com.bam.GESTIBANKBAM.service.ClientService;
import com.bam.GESTIBANKBAM.service.CompteService;

@RestController
public class SpecialOpsController {
	

	@Autowired
	private CompteService compteService;

	@RequestMapping (value= "/ongoingTransactions/{cid}", method= RequestMethod.GET)
	public ResponseEntity<List<Double>> ongoingTransactions(@PathVariable ("cid") Long cid) {
		List <Double> ops = compteService.findOngoingTransactionAmounts(cid);

		return new ResponseEntity<List<Double>>(ops, HttpStatus.OK);
	}
}
