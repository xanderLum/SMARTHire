package com.smarthire.main.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.JobPostApplicantsDao;
import com.smarthire.main.dao.JobSeekerDao;
import com.smarthire.main.models.JobPost;
import com.smarthire.main.models.JobPostApplicants;
import com.smarthire.main.models.JobSeeker;

@Service
public class JobPostApplicantsServiceImpl implements JobPostApplicantsService {
	@Autowired
	JobPostApplicantsDao jobPostApplicantsDao;

	@Autowired
	JobSeekerDao jobSeekerDao;

	@Override
	public ResponseEntity<JobPostApplicants> create(JobPostApplicants jpa) {
		// check first if jobpost exist. then check if applicant already exist
		ResponseEntity<JobPostApplicants> rejpa = read(jpa.getJob_post_id());
		System.out.println("+++++++++++++++++Here create1");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		if (rejpa.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("+++++++++++++++++Here create 2 status okay");
			try {
				if (rejpa.getBody().getUsername() != null
						&& rejpa.getBody().getUsername().equalsIgnoreCase(jpa.getUsername())) {
					System.out.println("Applicant =" + jpa.getUsername() + "= already applied for job id: "
							+ jpa.getJob_post_id() + " applicant in db:" + rejpa.getBody().getUsername());
					return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
				} else {
					jpa.setDate_applied(dateFormat.format(date));
					try {
						jobPostApplicantsDao.create(jpa);
						System.out.println("Success in JobPostApplicants Service Impl create");
						return new ResponseEntity<JobPostApplicants>(jpa, HttpStatus.OK);
					} catch (Exception ex) {
						System.out.println("Catch in JobPost Service Impl create: " + ex);
						return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
					}
				}
			} catch (Exception e) {
				System.out.println("Job Post Applicant not exist. need to create");
				jpa.setDate_applied(dateFormat.format(date));
				try {
					jobPostApplicantsDao.create(jpa);
					System.out.println("Success in JobPostApplicants Service Impl create");
					return new ResponseEntity<JobPostApplicants>(jpa, HttpStatus.OK);
				} catch (Exception ex) {
					System.out.println("Catch in JobPost Service Impl create: " + ex);
					return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
				}
			}
		} else {
			System.out.println(
					"Catch in JobPost Service Impl create: JobPost Service Impl create not returned httpstatus.OK");
			return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<JobPostApplicants> update(JobPostApplicants jpa) {
		//blank
		return null;
	}

	@Override
	public ResponseEntity<JobPostApplicants> delete(Long id) {
		try {
			JobPostApplicants jp = jobPostApplicantsDao.delete(id);
			System.out.println("Success in JobPostApplicants Service Impl delete");
			return new ResponseEntity<JobPostApplicants>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPostApplicants Service Impl delete: " + e);
			return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPostApplicants>> getList() {
		try {
			List<JobPostApplicants> ljp = jobPostApplicantsDao.getList();
			System.out.println("Success in JobPostApplicants Service Impl getList");
			return new ResponseEntity<List<JobPostApplicants>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPostApplicants Service Impl getList: " + e);
			return new ResponseEntity<List<JobPostApplicants>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPostApplicants> read(Long id) {
		try {
			System.out.println("Here in JobPostApplicants Service Impl read");
			JobPostApplicants jpa = jobPostApplicantsDao.read(id);
			return new ResponseEntity<JobPostApplicants>(jpa, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobPostApplicants Service Impl read: " + e);
			return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobSeeker>> getAllApplicants(Long job_post_id) {
		try {
			ArrayList<JobSeeker> ljs = new ArrayList<>();
			JobSeeker js;
			List<JobPostApplicants> ljp = jobPostApplicantsDao.getAllApplicants(job_post_id);
			for (JobPostApplicants jpa : ljp) {
				System.out.println(
						"================adding applicant:" + jpa.getUsername() + "===========================");
				js = jobSeekerDao.read(jpa.getUsername());
				System.out.println(
						"================able to read applicant:" + js.getUsername() + "===========================");
				ljs.add(js);
				System.out.println("================ljs length:" + ljs.size() + "===========================");
			}
			System.out.println("Success getAllApplicants service impl");
			return new ResponseEntity<List<JobSeeker>>(ljs, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in getAllApplicants service impl: " + e);
			return new ResponseEntity<List<JobSeeker>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPostApplicants>> getAllJobPosts(String username) {
		try {
			List<JobPostApplicants> ljp = jobPostApplicantsDao.getAllJobPosts(username);
			System.out.println("Success getAllJobPosts service impl");
			return new ResponseEntity<List<JobPostApplicants>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in getAllJobPosts sma: " + e);
			return new ResponseEntity<List<JobPostApplicants>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPostApplicants> checkExistApplication(Long job_post_id, String username) {
		try {
			JobPostApplicants ljp = jobPostApplicantsDao.checkExistApplication(job_post_id,username);
			System.out.println("Success getAllJobPosts service impl");
			return new ResponseEntity<JobPostApplicants>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in getAllJobPosts sma: " + e);
			return new ResponseEntity<JobPostApplicants>(HttpStatus.BAD_REQUEST);
		}
	}

}
