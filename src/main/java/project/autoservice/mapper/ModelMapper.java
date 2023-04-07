package project.autoservice.mapper;

public interface ModelMapper<M, P, R> {
    M toModel(R request);

    P toDto(M model);
}
