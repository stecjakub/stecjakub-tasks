package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {
    private final TrelloClient trelloClient;

    /*
    @GetMapping("boards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
        });
    }


    @GetMapping("boards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList -> {
                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
            });
        });
    }

     */
    @GetMapping("boards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(board -> board.getId() != null && board.getName() != null && board.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {
                    System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
                    System.out.println("This board contains lists: ");
                    trelloBoardDto.getLists().forEach(trelloList -> {
                        System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
                    });
                });
    }
    @PostMapping("cards")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
