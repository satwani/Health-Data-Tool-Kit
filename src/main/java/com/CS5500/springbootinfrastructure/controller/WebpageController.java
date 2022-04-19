package com.CS5500.springbootinfrastructure.controller;

import com.CS5500.springbootinfrastructure.dao.Activity;
import com.CS5500.springbootinfrastructure.dao.DateLog;
import com.CS5500.springbootinfrastructure.dao.Move;
import com.CS5500.springbootinfrastructure.dao.Type;
import com.CS5500.springbootinfrastructure.repos.DateLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Controller
public class WebpageController {

    @Autowired
    private DateLogRepository dateRepo;


    @GetMapping("records/delete/{date}")
    public String deleteLog(@PathVariable("date") Date date, Model model) {
        try {
            dateRepo.deleteById(date);
            model.addAttribute("return_url", "/records");
            return "update_success";
        } catch(Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("error", "could not delete log");
        return "error_page";
    }

    @Transactional
    @GetMapping("records/delete/{date}/move/{typeid}")
    public String deleteType(@PathVariable("date") Date date, @PathVariable("typeid") long id, Model model) {
        try {
            Optional<DateLog> opt = dateRepo.findById(date);
            if (opt.isEmpty()) {
                return "error_page";
            }

            DateLog dl = opt.get();
            List<Type> lt = dl.getTypes();

            dateRepo.deleteTypeById(id);
            model.addAttribute("return_url", "/records/" + date + "/types");
            return "update_success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error_page";
        }
    }

    @PostMapping("/records/add-record")
    public String greetingSubmit(@ModelAttribute DateLogContModel newLog, Model model) {
        model.addAttribute("newLog", newLog);

        DateLog dl = new DateLog();
        dl.setDate(Date.valueOf(newLog.getYyyy() + "-"
        + newLog.getMm() + "-"
        + newLog.getDd()));
        dl.setCaloriesIdle(newLog.getCaloriesIdle());
        dl.timestampLastUpdate();

        dateRepo.save(dl);
        model.addAttribute("return_url", "/records");
        return "update_success";
    }
    @PutMapping("/records/edit-record")
    public String editSubmit(@ModelAttribute DateLogContModel newLog, Model model) {
        model.addAttribute("newLog", newLog);

        DateLog dl = dateRepo.getDateLogByDateIs(Date.valueOf(newLog.getYyyy() + "-"
            + newLog.getMm() + "-"
            + newLog.getDd()));
        dl.setCaloriesIdle(newLog.getCaloriesIdle());
        dl.timestampLastUpdate();
        dateRepo.save(dl);

        return "update_success";
    }


    /*@PostMapping("/records/{date}/add-type")
    public String addType(Type type) {
        DateLog dl = dateRepo.findById(date);
        List<Type> lt = dl.getTypes();
        lt.add(type);
        dl.setTypes(lt);
        dateRepo.save(dl);
    }*/

    @GetMapping("/records/add-record")
    public String addRecordPage(Model model) {
        model.addAttribute("newLog", new DateLogContModel());
        return "date_post";
    }

    @GetMapping("/records/{date}/add-type/move")
    public String getAddTypePage(@PathVariable Date date, Model model) {
        model.addAttribute("newType", new TypeContModel());
        model.addAttribute("date", date);
        return "move_post";
    }

    @PostMapping("/records/{date}/add-type/move")
    public String addType(@PathVariable("date") Date date, @ModelAttribute TypeContModel type, Model model) {
        Optional<DateLog> opt = dateRepo.findById(date);
        if (opt.isEmpty()) {
            return "error_page";
        }

        DateLog dl = opt.get();
        List<Type> lt = dl.getTypes();
        Move toAdd = new Move();
        toAdd.setDate(dl);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int yyyy = cal.get(Calendar.YEAR);
        int mm = cal.get(Calendar.MONTH) + 1;
        int dd = cal.get(Calendar.DAY_OF_MONTH);

        int hh = type.start_hh;
        int min = type.start_mm;
        int ss = type.start_ss;
        Timestamp start = Timestamp.valueOf(yyyy + "-" + mm + "-" + dd + " " + hh + ":" +
                min + ":" + ss);
        toAdd.setStartTime(start);

        hh = type.end_hh;
        min = type.end_mm;
        ss = type.end_ss;

        Timestamp end = Timestamp.valueOf(yyyy + "-" + mm + "-" + dd + " " + hh + ":" +
                min + ":" + ss);
        toAdd.setEndTime(end);
        toAdd.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        lt.add(toAdd);
        dl.setTypes(lt);
        dateRepo.save(dl);

        model.addAttribute("return_url", "/records/" + date + "/types");
        return "update_success";
    }

    @GetMapping("/records/edit-record")
    public String editRecordPage(Model model) {
        model.addAttribute("newLog", new DateLogContModel());
        return "date_Edit";
    }
    @GetMapping("/records/edit-record/{date}")
    public String editSubmit(@PathVariable("date") Date date, Model model) {
        model.addAttribute("newLog", new DateLogContModel());
        model.addAttribute("date", date);
        return "edit_this";
    }
    @PutMapping("/records/edit-record/{date}")
    public String editThisRecord(@ModelAttribute DateLogContModel newLog, @PathVariable("date") Date date,Model model) {
        model.addAttribute("newLog", new DateLogContModel());
        DateLog dl = dateRepo.getDateLogByDateIs(date);
        dl.setCaloriesIdle(newLog.getCaloriesIdle());
        dl.timestampLastUpdate();
        dateRepo.save(dl);
        return "update_success";
    }

    @GetMapping(path="/records")
    public String fetchHomePage(Model model) {
        List<DateLog> logs = dateRepo.getJSONDates();
        model.addAttribute("allDates", logs);
        return "homepage";
    }

    @GetMapping(path = "/records/types")
    public String fetchTypes(Model model) {
        List<Type> types = dateRepo.getJSONTypes();
        model.addAttribute("allTypes", types);
        return "types_all";
    }

    @PutMapping("/records/{date}/edit-record")
    public String editDateLog(@PathVariable("date") Date date, Model model) {

        return "save_success";
    }

    @GetMapping(path = "/records/{date}/types")
    public String fetchTypesByDateId(@PathVariable("date") Date date, Model model) {
        Optional optionalDL = dateRepo.findById(date);
        if (optionalDL.isPresent()) {
            DateLog dateLog = (DateLog) optionalDL.get();
            List<Type> types = dateLog.getTypes();
            model.addAttribute("typesForDate", types);
            model.addAttribute("date", date);

            return "types_id";
        }

        return "types_error";
    }



    @GetMapping(path = "records/types/{tid}/activities")
    public String fetchActivitiesByTypeId(@PathVariable("tid") long tid, Model model) {
        Type type = dateRepo.getTypeById(tid);

        if (type != null) {
            List<Activity> activities = type.getActivities();
            model.addAttribute("activitiesForId", activities);

            return "activities_id";
        }

        return "activities_error";
    }

    @PostMapping("/records/types/{tid}/add-activity")
    public String addType(@PathVariable("tid") long tid, @ModelAttribute ActivityContModel activity, Model model) {
        Type type = dateRepo.getTypeById(tid);
        if (type == null) {
            return "error_page";
        }

        List<Activity> act = type.getActivities();

        Activity toAdd = new Activity();

        toAdd.setAct_group(activity.getGroup());

        long startTime = type.getStartTime().getTime();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime);
        int yyyy = cal.get(Calendar.YEAR);
        int mm = cal.get(Calendar.MONTH);
        int dd = cal.get(Calendar.DAY_OF_MONTH);

        int start_hh = activity.getStart_hh();
        int start_mm = activity.getStart_mm();
        int start_ss = activity.getStart_ss();
        Timestamp start = Timestamp.valueOf(yyyy + "-" + mm + "-" + dd + " " + start_hh + ":" +
                start_mm + ":" + start_ss);
        toAdd.setStartTime(start);

        int end_hh = activity.getEnd_hh();
        int end_mm = activity.getEnd_mm();
        int end_ss = activity.getEnd_ss();
        Timestamp end = Timestamp.valueOf(yyyy + "-" + mm + "-" + dd + " " + end_hh + ":" +
                end_mm + ":" + end_ss);
        toAdd.setEndTime(end);

        toAdd.setCalories(activity.getCalories());
        toAdd.setDistance(activity.getDistance());
        toAdd.setDuration(activity.getDuration());
        toAdd.setManual(activity.getManual());
        toAdd.setSteps(activity.getSteps());

        act.add(toAdd);
        return "update_success";
    }

}
