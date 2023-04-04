package project.autoservice.service.mapper;

public interface ModelMapper<M, P, R> {
    M toModel(R request);

    P toDto(M model);
}
