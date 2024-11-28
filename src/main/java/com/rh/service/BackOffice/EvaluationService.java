package com.rh.service.BackOffice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Evaluation;
import com.rh.repository.EvaluationRepository;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository er) {
        this.evaluationRepository = er;
    }

    // public Optional<Evaluation> getEvaluation(int idCandidature) {
    //     return this.evaluationRepository.findByCandidature_IdCandidature(idCandidature);
    // }

    public Evaluation enregistrerEvaluation(Evaluation evaluation) {
        return this.evaluationRepository.save(evaluation);
    }
}
