package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.CertificateDao;
import com.smarthire.xander.models.Certificate;

@Service
public class CertificateServiceImpl implements CertificateService {
	@Autowired
	CertificateDao certificateDao;

	@Override
	public ResponseEntity<Certificate> create(Certificate jp) {
		try {
			certificateDao.create(jp);
			return new ResponseEntity<Certificate>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Certificate>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Certificate> update(Certificate jp) {
		try {
			certificateDao.update(jp);
			return new ResponseEntity<Certificate>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Certificate>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Certificate> delete(Long id) {
		try {
			Certificate jp = certificateDao.delete(id);
			return new ResponseEntity<Certificate>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Certificate>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Certificate>> getList() {
		try {
			List<Certificate> ljp = certificateDao.getList();
			System.out.println("Success in Certificate Service Impl getList");
			return new ResponseEntity<List<Certificate>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in Certificate Service Impl getList: " + e);
			return new ResponseEntity<List<Certificate>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Certificate> read(Long id) {
		try {
			Certificate jp = certificateDao.read(id);
			System.out.println("Success in Certificate Service Impl read");
			return new ResponseEntity<Certificate>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in Certificate Service Impl read: " + e);
			return new ResponseEntity<Certificate>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Certificate> read(String username, String name) {
		try {
			Certificate ljp = certificateDao.read(username, name);
			System.out.println("Success Certificate service impl");
			return new ResponseEntity<Certificate>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Certificate>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Certificate>> getAllCertificatesByUser(String username) {
		try {
			List<Certificate> ljp = certificateDao.getAllCertificatesByUser(username);
			System.out.println("Success Certificate service impl");
			return new ResponseEntity<List<Certificate>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Certificate>>(HttpStatus.BAD_REQUEST);
		}
	}
}
