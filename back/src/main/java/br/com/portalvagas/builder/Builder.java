package br.com.portalvagas.builder;

import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.controller.response.JobResponse;
import br.com.portalvagas.entity.Job;
import br.com.portalvagas.entity.User;
import br.com.portalvagas.enums.ExpertiseLevel;
import br.com.portalvagas.enums.RoleUser;
import br.com.portalvagas.enums.State;
import br.com.portalvagas.enums.TypeOfJob;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.portalvagas.utils.DateUtil.createDateNow;
import static br.com.portalvagas.utils.DateUtil.createExpirationDate;
import static br.com.portalvagas.utils.PasswordUtil.hashPassword;

public class Builder {


    public static User createUser(UserRequest user){
        return User.builder()
                .username(user.name())
                .password(hashPassword(user.password()))
                .email(user.email())
                .roleUser(RoleUser.USER)
                .build();
    }

    public static Job createJob(JobRequest job){
        LocalDate date= createDateNow();
        return Job.builder()
                .city(job.city())
                .title(job.title())
                .description(job.description())
                .location(job.location())
                .accept_pcd(job.accept_pcd())
                .state(State.valueOf(job.state()))
                .typeOfJob(TypeOfJob.valueOf(job.typeOfJob()))
                .company(job.company())
                .expertiseLevel(ExpertiseLevel.valueOf(job.expertiseLevel()))
                .temporary(job.temporary())
                .publishDate(date)
                .expireDate(createExpirationDate(date))
                .build();
    }

    public static JobResponse createJobResponse(Job job){
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .typeOfJob(job.getTypeOfJob().toString())
                .company(job.getCompany())
                .expertiseLevel(job.getExpertiseLevel().toString())
                .temporary(job.isTemporary())
                .city(job.getCity())
                .state(job.getState().toString())
                .publishDate(job.getPublishDate().toString())
                .expireDate(job.getExpireDate().toString())
                .build();
    }
}
