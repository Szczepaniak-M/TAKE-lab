package lab.bookings.controllers;


import lab.bookings.models.Apartment;
import lab.bookings.models.Booking;
import lab.bookings.repositories.ApartmentRepository;
import lab.bookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final ApartmentRepository apartmentRepository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(new Booking());
        model.addAttribute("bookingToDelete", new Booking());
        model.addAttribute("bookings", bookingRepository.findAll());
        return "bookings";
    }

    @PostMapping
    public String create(@Valid Booking booking, Errors errors,
                         Model model) {
        String view;
        if (errors.hasErrors()) {
            model.addAttribute("bookings", bookingRepository.findAll());
            view = "bookings";
        } else {
            int numGuests = booking.getNumGuests();
            LocalDate startDay = booking.getFromDate();
            LocalDate endDay = booking.getToDate();
            List<Apartment> availableApartments =
                    apartmentRepository.getFreeApartments(numGuests, startDay, endDay);

            if (!availableApartments.isEmpty()) {
                booking.setApartment(availableApartments.get(0));
                bookingRepository.save(booking);
                view = "redirect:/bookings";
            } else {
                model.addAttribute("noApartmentAvailable", true);
                model.addAttribute("bookings",
                        bookingRepository.findAll());
                view = "bookings";
            }
        }
        return view;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        bookingRepository.deleteById(id);
        return "redirect:/bookings";
    }
}
