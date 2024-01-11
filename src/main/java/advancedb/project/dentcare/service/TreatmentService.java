package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Treatment;
import advancedb.project.dentcare.dto.UpdateTreatmentRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    public List<Treatment> findByTreatmentPlan(Integer treatmentPlanId){
        return treatmentRepository.findByTreatmentPlan_Id(treatmentPlanId);
    }
    public List<Treatment> findByStatusAndTreatmentPlanId(Integer treatmentPlanId, String status){
        return null;
    }

    public Map<LocalDate, Integer> getStatisticByDateAndDentist(LocalDate from, LocalDate to, int dentistId) {
       Map<LocalDate, Integer> treatmentCountByDentistAndDate = new HashMap<>();
       List<LocalDate> dates = from.datesUntil(to).toList();
       for(LocalDate date: dates){
           treatmentCountByDentistAndDate.put(date, 0);
       }
       List<Object[]> result = treatmentRepository.getTreatmentCountByDateAndDentist(dentistId, from, to);
        for(Object[] row: result){

            Date date = (Date) row[0];
            int count = (int) row[1];
            treatmentCountByDentistAndDate.put(date.toLocalDate(), count);
        }
        return treatmentCountByDentistAndDate;
    }

    public Treatment updateTreatment(UpdateTreatmentRequest request) {
        Treatment treatmentToUpdate = treatmentRepository.findById(request.getTreatmentId())
                .orElseThrow(() -> new ResourceNotFoundException("No treatment with id = " + request.getTreatmentId()));
        treatmentToUpdate.setFee(request.getFee());
        treatmentToUpdate.setDescription(request.getNote());
        treatmentToUpdate.setPerformAt(request.getPerformAt());
        return treatmentRepository.save(treatmentToUpdate);
    }
}
