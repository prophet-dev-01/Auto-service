package project.autoservice.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Master;
import project.autoservice.model.Service;
import project.autoservice.model.dto.request.MasterRequestDto;
import project.autoservice.model.dto.request.ServiceRequest;
import project.autoservice.model.dto.response.MasterResponseDto;
import project.autoservice.model.dto.response.ServiceResponse;
import project.autoservice.service.MasterService;
import project.autoservice.service.ServiceService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/masters")
public class MasterController {
    private final ModelMapper<Service,
            ServiceResponse,
            ServiceRequest> serviceMapper;
    private final ModelMapper<Master,
            MasterResponseDto,
            MasterRequestDto> masterMapper;
    private final MasterService masterService;
    private final ServiceService operationService;

    @PostMapping
    public MasterResponseDto create(@RequestBody MasterRequestDto requestDto) {
        Master master = masterService.save(masterMapper.toModel(requestDto));
        return masterMapper.toDto(master);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MasterRequestDto requestDto) {
        Master master = masterMapper.toModel(requestDto);
        master.setId(id);
        masterService.save(master);
    }

    @GetMapping("/{id}/service")
    public List<ServiceResponse> findAllServiceOperationsByMasterId(
            @PathVariable Long id) {
        return operationService.findAllByMasterId(id)
                .stream().map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    public BigDecimal getSalary(@PathVariable Long id) {
        return masterService.getSalary(id);
    }
}

