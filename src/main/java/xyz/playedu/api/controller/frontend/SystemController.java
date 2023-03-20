package xyz.playedu.api.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.playedu.api.service.AppConfigService;
import xyz.playedu.api.service.ImageCaptchaService;
import xyz.playedu.api.types.ImageCaptchaResult;
import xyz.playedu.api.types.JsonResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 杭州白书科技有限公司
 * @create 2023/3/13 11:26
 */
@RestController
@RequestMapping("/api/v1/system")
public class SystemController {

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private ImageCaptchaService imageCaptchaService;

    @GetMapping("/config")
    public JsonResponse config() {
        Map<String, String> configs = appConfigService.keyValues();

        HashMap<String, String> data = new HashMap<>();

        data.put("system-name", configs.get("system.name"));
        data.put("system-logo", configs.get("system.logo"));
        data.put("system-api-url", configs.get("system.api_url"));
        data.put("system-pc-url", configs.get("system.pc_url"));
        data.put("system-h5-url", configs.get("system.h5_url"));

        return JsonResponse.data(data);
    }

    @GetMapping("/image-captcha")
    public JsonResponse imageCaptcha() throws IOException {
        ImageCaptchaResult imageCaptchaResult = imageCaptchaService.generate();

        HashMap<String, String> data = new HashMap<>();
        data.put("key", imageCaptchaResult.getKey());
        data.put("image", imageCaptchaResult.getImage());

        return JsonResponse.data(data);
    }

}
