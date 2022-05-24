package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.service.CodeSharingPlatformServiceImpl;

@Controller
@RequestMapping(value = "/code", produces = "text/html")
public class CodeSharingPlatformController {

    private static final String CODE_FRAGMENT_TEMPLATE = "getCodeSnippet";
    private static final String LATEST_FRAGMENTS_TEMPLATE = "latestCodeSnippets";
    private static final String POST_SNIPPET_TEMPLATE = "postCodeSnippet";
    private final CodeSharingPlatformServiceImpl service;

    @Autowired
    public CodeSharingPlatformController(CodeSharingPlatformServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public String tranferCodeN(@PathVariable String id, Model model) {
        var result = service.getCodeFragment(id);
        model.addAttribute("code", result.getCode());
        model.addAttribute("isTimeRestricted", result.isTimeRestricted());
        model.addAttribute("isViewRestricted", result.isViewRestricted());
        System.out.println("code: " + result.getCode() + ", views:" + result.getCode().getViews() + " " + ", isTimeRestricted: " + result.isTimeRestricted() + ", isViewRestricted: " + result.isViewRestricted());
        return CODE_FRAGMENT_TEMPLATE;
    }

    @GetMapping(value = "/new")
    public String postCode() {
        return POST_SNIPPET_TEMPLATE;
    }

    @GetMapping(value = "/latest")
    public String tranferLatestCode(Model model) {
        model.addAttribute("codes", service.getCodeLatestFragments());
        return LATEST_FRAGMENTS_TEMPLATE;
    }

}
