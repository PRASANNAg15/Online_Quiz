package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.QuestionDto;
import com.example.OnlineQuiz.Entity.QuestionEntity;
import com.example.OnlineQuiz.Repository.QuestionRepository;
import com.example.OnlineQuiz.UpdateDto.QuestionUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;

    public QuestionDto add(QuestionDto dto) {
        QuestionEntity questionEntity=new QuestionEntity();
        questionEntity.setQuestion(dto.getQuestion());
        questionEntity.setOption1(dto.getOption1());
        questionEntity.setOption2(dto.getOption2());
        questionEntity.setOption3(dto.getOption3());
        questionEntity.setOption4(dto.getOption4());
        questionEntity.setCorrectOption(dto.getCorrectOption());
        questionEntity.setQuizId(dto.getQuizId());
        QuestionEntity saved=questionRepository.save(questionEntity);
        return modelMapper.map(saved, QuestionDto.class);
    }

    public List<QuestionDto> findAllQuestions() {
        List<QuestionEntity> questions=questionRepository.findAll();
        List<QuestionDto> filtered=questions.stream().map(question->modelMapper.map(question, QuestionDto.class)).toList();
        return filtered;
    }

    public QuestionDto QuestionById(long id) {
        QuestionEntity question=questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found"));
        return modelMapper.map(question, QuestionDto.class);
    }

    public QuestionUpdateDto update(long id, QuestionUpdateDto dto) {
        QuestionEntity questionEntity=questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found"));
        questionEntity.setQuestion(dto.getQuestion());
        questionEntity.setOption1(dto.getOption1());
        questionEntity.setOption2(dto.getOption2());
        questionEntity.setOption3(dto.getOption3());
        questionEntity.setOption4(dto.getOption4());
        questionEntity.setCorrectOption(dto.getCorrectOption());
        questionEntity.setQuizId(dto.getQuizId());
        QuestionEntity updated=questionRepository.save(questionEntity);
        return modelMapper.map(updated, QuestionUpdateDto.class);
    }

    public void delete(long id) {
        QuestionEntity question=questionRepository.findById(id).orElseThrow(()->new RuntimeException("Question Not Found "));
        questionRepository.deleteById(id);
    }
}
