package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.dto.CodeMessage;
import platform.model.dto.NewCodeRequest;
import platform.model.dto.NewCodeResponse;
import platform.service.CodeSharingPlatformServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/api/code")
public class CodeSharingApiController {

    private final CodeSharingPlatformServiceImpl service;

    @Autowired
    public CodeSharingApiController(CodeSharingPlatformServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public CodeMessage getCodeN(@PathVariable String id) {
        return service.getCodeFragment(id).getCode();
    }

    @GetMapping(value = "/new")
    public CodeMessage getCodeNew() {
        return service.getCodeNewFragment().getCode();
    }

    @GetMapping("/latest")
    public List<CodeMessage> getCodeLatest() {
        return service.getCodeLatestFragments();
    }

    @PostMapping(value = "/new")
    public NewCodeResponse sendNewCode(@RequestBody NewCodeRequest request) {
        return service.saveCodeFragment(request);
    }

}