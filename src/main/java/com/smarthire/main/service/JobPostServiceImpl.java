package com.smarthire.main.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.JobPostDao;
import com.smarthire.main.models.JobPost;

@Service
public class JobPostServiceImpl implements JobPostService {
	@Autowired
	JobPostDao jobPostDao;

	@Override
	public ResponseEntity<JobPost> create(JobPost jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			jp.setDate_posted(dateFormat.format(date));

			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("closing date = " + jp.getClosing_date());
			
			Date closing_date = inputFormat.parse(jp.getClosing_date());
			jp.setJob_status("available");
			jp.setClosing_date(dateFormat.format(closing_date));
			
			System.out.println("toString job post before add == "+jp.toString());
			jobPostDao.create(jp);
			System.out.println("Success in JobPost Service Impl create");
			return new ResponseEntity<JobPost>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPost Service Impl create: " + e);
			e.printStackTrace();
			return new ResponseEntity<JobPost>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPost> update(JobPost jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			jp.setDate_posted(dateFormat.format(date));
			
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("closing date = " + jp.getClosing_date());
			Date closing_date = inputFormat.parse(jp.getClosing_date());
			jp.setJob_status("available");
			jp.setClosing_date(dateFormat.format(closing_date));
			
			jobPostDao.update(jp);
			System.out.println("Success in JobPost Service Impl update");
			return new ResponseEntity<JobPost>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPost Service Impl update: " + e);
			return new ResponseEntity<JobPost>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPost> delete(Long id) {
		try {
			JobPost jp = jobPostDao.delete(id);
			System.out.println("Success in JobPost Service Impl delete");
			return new ResponseEntity<JobPost>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPost Service Impl delete: " + e);
			return new ResponseEntity<JobPost>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPost>> getList() {
		try {
			List<JobPost> ljp = jobPostDao.getList();
			System.out.println("Success in JobPost Service Impl getList");
			return new ResponseEntity<List<JobPost>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPost Service Impl getList: " + e);
			return new ResponseEntity<List<JobPost>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPost> read(Long id) {
		try {
			JobPost jp = jobPostDao.read(id);
			System.out.println("Success in JobPost Service Impl read");
			return new ResponseEntity<JobPost>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPost Service Impl read: " + e);
			return new ResponseEntity<JobPost>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPost>> getPostByEmployer(String username) {
		try {
			List<JobPost> ljp = jobPostDao.getPostByEmployer(username);
			System.out.println("Success getPostByEmployer service impl");
			return new ResponseEntity<List<JobPost>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in getPostByEmployer sma: " + e);
			return new ResponseEntity<List<JobPost>>(HttpStatus.BAD_REQUEST);
		}
	}

}
