package com.example.CookingBook.web.controllers;

import com.example.CookingBook.models.DTO.CommentDTO;
import com.example.CookingBook.models.DTO.ReceiptViewDTO;
import com.example.CookingBook.models.entity.CommentEntity;
import com.example.CookingBook.models.entity.ReceiptEntity;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.repository.CommentRepository;
import com.example.CookingBook.services.ReceiptService;
import com.example.CookingBook.services.UserService;
import com.example.CookingBook.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/receipes")
public class ReceipesViewController {
    private final ReceiptService receiptService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public ReceipesViewController(ReceiptService receiptService, ModelMapper modelMapper, UserService userService, CommentRepository commentRepository) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/view_all/{page}")
    @PageTitle("View recipes")
    public String getAllReceipesView(Model model, @PathVariable int page) {

        Page<ReceiptEntity> receiptsPageable = receiptService.getPageableReceiptEntityPage(page);
        List<ReceiptViewDTO> receipts = receiptsPageable
                .getContent()
                .stream()
                .map(receiptEntity -> modelMapper.map(receiptEntity, ReceiptViewDTO.class)).
                toList();


        model.addAttribute("isNextVisible", receiptsPageable.getTotalPages() < receiptsPageable.getPageable().getPageNumber());
        model.addAttribute("isPrevVisible", receiptsPageable.getPageable().getPageNumber() > 0);
        model.addAttribute("currentPage", receiptsPageable.getPageable().getPageNumber());
        model.addAttribute("receipts", receipts);


        return "receipes/viewAll";
    }

    @GetMapping("/view_user_recipes/{page}")
    @PageTitle("View recipes")
    public String getAllUserReceipesView(Model model, @PathVariable int page, Principal principal) {

        UserEntity user = userService.getUserByEmail(principal.getName());
        Page<ReceiptEntity> receiptsPageable = receiptService
                .getPageableReceiptEntityPageByUser(user, page);
        List<ReceiptViewDTO> receipts = receiptsPageable
                .getContent()
                .stream()
                .map(receiptEntity -> modelMapper.map(receiptEntity, ReceiptViewDTO.class)).
                toList();

        model.addAttribute("isEmpty",receipts.isEmpty());
        model.addAttribute("isNextVisible", receiptsPageable.getTotalPages() < receiptsPageable.getPageable().getPageNumber());
        model.addAttribute("isPrevVisible", receiptsPageable.getPageable().getPageNumber() > 0);
        model.addAttribute("currentPage", receiptsPageable.getPageable().getPageNumber());
        model.addAttribute("receipts", receipts);


        return "receipes/viewAllByUser";
    }
    @GetMapping("/view_user_recipes")
    @PageTitle("View recipes")
    public String getAllUserReceipesView() {

        return "redirect:/receipes/view_user_recipes/0";
    }


    @GetMapping("/view_all")
    @PageTitle("View recipes")
    public String getAllReceipesView(Model model) {
        return "redirect:/receipes/view_all/0";
    }

    @GetMapping("/view/{receiptID}")
    @PageTitle("View recipe")
    public String getReceipesView
            (Model model, @PathVariable Long receiptID,Principal principal) {
        UserEntity user = userService.getUserByEmail(principal.getName());
        ReceiptEntity receiptEntity =receiptService.findById(receiptID);
        ReceiptViewDTO receipt = modelMapper.map(receiptEntity, ReceiptViewDTO.class);
        model.addAttribute("receipt", receipt);
        model.addAttribute("isReceiptOnUser", Objects.equals(receiptEntity.getUser().getId(), user.getId()) || user.getRoles().size()>0);
        model.addAttribute("id",receipt.getId());
        model.addAttribute("comments", receiptEntity.getComments());
        return "receipes/receiptView";
    }

    @PostMapping("/comment/{recipeID}")
    public String comment(@PathVariable long recipeID,
                          @ModelAttribute CommentDTO commentDTO,
                          Principal principal){
        UserEntity user = userService.getUserByEmail(principal.getName());
        CommentEntity comment = new CommentEntity();
        comment.setComment(commentDTO.getComment());
        comment.setUserEntity(user);

        commentRepository.save(comment);
        ReceiptEntity  receipt= receiptService.findById(recipeID);

        if (receipt.getComments() == null){
            receipt.setComments(new ArrayList<>());
        }
        receipt.getComments().add(comment);
        receiptService.saveAndFlush(receipt);

        return "redirect:/receipes/view/" + recipeID;

    }


    @ModelAttribute(name = "commentDTO")
    public CommentDTO commentDTO(){
        return new CommentDTO();
    }

}
