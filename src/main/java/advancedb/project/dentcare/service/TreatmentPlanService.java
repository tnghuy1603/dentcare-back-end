package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Invoice;
import advancedb.project.dentcare.domain.TreatmentPlan;
import advancedb.project.dentcare.dto.AddTreatmentPlanRequest;
import advancedb.project.dentcare.repository.PatientRepository;
import advancedb.project.dentcare.repository.TreatmentPlanRepository;
import advancedb.project.dentcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentPlanService {
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    public List<TreatmentPlan> findByPatient(Integer patientId){
        return treatmentPlanRepository.findByPatient_Id(patientId);
    }

    public TreatmentPlan addTreatmentPlan(AddTreatmentPlanRequest request) {
        TreatmentPlan treatmentPlan = TreatmentPlan.builder()
                .note(request.getNote())
                .startingDate(request.getStartingDate())
                .status(request.getStatus())
                .build();
        Invoice invoice = new Invoice();

        

        return treatmentPlanRepository.save(treatmentPlan);
    }


}
