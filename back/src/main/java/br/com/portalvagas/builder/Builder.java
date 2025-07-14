package br.com.portalvagas.builder;

import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.controller.response.*;
import br.com.portalvagas.entity.Job;
import br.com.portalvagas.entity.User;
import br.com.portalvagas.enums.RoleUser;
import br.com.portalvagas.exception.response.ErrorResponse;
import br.com.portalvagas.exception.response.ErrorResponseList;
import org.springframework.data.domain.Page;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import static br.com.portalvagas.utils.DateUtil.createDateNow;
import static br.com.portalvagas.utils.DateUtil.createExpirationDate;
import static br.com.portalvagas.utils.PasswordUtil.hashPassword;

public class Builder {


    public static User createUser(UserRequest user){
        return User.builder()
                .name(user.name())
                .password(hashPassword(user.password()))
                .email(user.email())
                .role(RoleUser.USER)
                .build();
    }

    public static Job createJob(JobRequest job){
        LocalDate date= createDateNow();
        return Job.builder()
                .city(job.city())
                .title(job.title())
                .description(job.description())
                .location(job.location())
                .acceptPcd(job.accept_pcd())
                .state(job.state())
                .typeOfJob(job.typeOfJob())
                .company(job.company())
                .expertiseLevel(job.expertiseLevel())
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
                .typeOfJob(job.getTypeOfJob())
                .company(job.getCompany())
                .expertiseLevel(job.getExpertiseLevel())
                .temporary(job.isTemporary())
                .city(job.getCity())
                .state(job.getState())
                .publishDate(job.getPublishDate().toString())
                .expireDate(job.getExpireDate().toString())
                .build();
    }

    public static UserNameResponse createUserNameResponse(String username) {
        return UserNameResponse.builder()
                .name(username)
                .build();
    }

    public static ErrorResponse createErrorResponse(String errorField, String message) {
        return  ErrorResponse.builder()
                .Message(message)
                .ErrorField(errorField)
                .build();
    }

    public static ErrorResponseList createErrorResponseList(List<ErrorResponse> errorResponseList) {
        return ErrorResponseList.builder()
                .detalis(errorResponseList)
                .build();
    }


    public static Function<Job, JobCardResponse> createJobCardResponseFunction() {
        return job -> new JobCardResponse(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getPublishDate().toString(),
                job.getExpireDate().toString(),
                job.getCity(),
                job.getState(),
                job.isTemporary()
        );
    }

    public static JobCardPageResponse createJobCardPageResponse(List<JobCardResponse> content, Page<Job> jobPage) {
        return new JobCardPageResponse(
                content,
                jobPage.getNumber(),
                jobPage.getSize(),
                jobPage.getTotalElements(),
                jobPage.getTotalPages()
        );
    }

    public static UserRoleResponse createUserRoleResponse(RoleUser role) {
        return UserRoleResponse.builder()
                .role(role.toString())
                .build();
    }
}
