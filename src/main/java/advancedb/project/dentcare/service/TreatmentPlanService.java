package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.*;
import advancedb.project.dentcare.dto.AddTreatmentPlanRequest;
import advancedb.project.dentcare.dto.ToothSelectionDto;
import advancedb.project.dentcare.dto.UpdateTreatmentPlanRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TreatmentPlanService {
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final TreatmentCodeRepository treatmentCodeRepository;
    private final TreatmentRepository treatmentRepository;
    private final ToothSelectionRepository toothSelectionRepository;
    private final InvoiceRepository invoiceRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final ToothSurfaceRepository toothSurfaceRepository;
    private final ToothRepository toothRepository;
    public List<TreatmentPlan> findByPatient(Integer patientId){
        return treatmentPlanRepository.findByPatient_Id(patientId);
    }
    @Transactional
    public TreatmentPlan addTreatmentPlan(AddTreatmentPlanRequest request) {
        User dentist = userRepository.findByRoleAndId("ROLE_DENTIST", request.getDentistId())
                .orElseThrow(() -> new ResourceNotFoundException("No dentist with id = "+ request.getDentistId()));
        User assistant = null;
        if(request.getAssistantId() != null){
            assistant = userRepository.findByRoleAndId("ROLE_DENTIST", request.getAssistantId())
                    .orElseThrow(() -> new ResourceNotFoundException("No dentist with id = "+ request.getAssistantId()));
        }
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("No patient with id = "+ request.getPatientId()));

        TreatmentPlan treatmentPlan = TreatmentPlan.builder()
                .note(request.getNote())
                .startDate(request.getStartingDate())
                .patient(patient)
                .dentist(dentist)
                .assistant(assistant)
                .status("Planned")
                .build();

        List<ToothSelection> toothSelectionList = new ArrayList<>();
        ToothSelection toothSelection = null;
        Tooth tooth = null;
        ToothSurface toothSurface = null;
        System.out.println(request.getToothSelectionDtoList().size());
        for(ToothSelectionDto toothSelectionDto: request.getToothSelectionDtoList()){
            tooth = toothRepository.findById(toothSelectionDto.getToothId()).orElseThrow(() -> new ResourceNotFoundException("No tooth with id = " + toothSelectionDto.getToothId()));
            for(Integer surfaceId: toothSelectionDto.getSurfacesId()){
                toothSelection = toothSelectionRepository.findByTooth_IdAndToothSurface_Id(toothSelectionDto.getToothId(), surfaceId);
                if(toothSelection == null){
                    toothSurface = toothSurfaceRepository.findById(surfaceId).orElseThrow(() -> new ResourceNotFoundException("No tooth surface id = " + toothSelectionDto.getToothId()));
                    toothSelection = ToothSelection.builder()
                            .tooth(tooth)
                            .toothSurface(toothSurface)
                            .build();
                }

            }
            toothSelectionList.add(toothSelection);
        }
        toothSelectionList = toothSelectionRepository.saveAll(toothSelectionList);

        Treatment treatment = null;
        List<Treatment> treatments = new ArrayList<>();
        TreatmentCode treatmentCode = null;
        for(int treatmentCodeId : request.getTreatmentCodeId()){
            treatmentCode = treatmentCodeRepository.findById(treatmentCodeId)
                    .orElseThrow(() -> new ResourceNotFoundException("No treatment code with id = " + treatmentCodeId));
            treatment = Treatment.builder()
                    .treatmentPlan(treatmentPlan)
                    .fee(treatmentCode.getFee())
                    .treatmentCode(treatmentCode)
                    .fee(treatmentCode.getFee())
                    .build();
            log.info("Treatment id" + treatment.getId());
            treatments.add(treatment);
        }
//        treatments = treatmentRepository.saveAll(treatments);
        treatmentPlan.setTreatments(treatments);

        treatmentPlan.setToothSelectionList(toothSelectionList);

        return  treatmentPlanRepository.save(treatmentPlan);

    }
    @Transactional
    public TreatmentPlan udpateTreatmentPlan(UpdateTreatmentPlanRequest request){
        Integer treatmentPlanId = request.getTreatmentPlanId();;
        TreatmentPlan treatmentPlanToUpdate = new TreatmentPlan();
        //update treatment plan before create invoice
        if(treatmentPlanToUpdate.getStatus().equals("Completed") && !invoiceRepository.existsById(treatmentPlanId)){
            BigDecimal prescriptionFee = prescriptionRepository.totalFeeByTreatmentPlan(treatmentPlanId);
            BigDecimal treatmentFee = treatmentRepository.getTotalFeeByTreatmentPlanId(treatmentPlanId);
            BigDecimal totalFee = prescriptionFee.add(treatmentFee);
            Invoice invoice = Invoice.builder()
                    .totalFee(totalFee)
                    .treatmentPlan(treatmentPlanToUpdate)
                    .build();
            invoiceRepository.save(invoice);
        }
        return treatmentPlanRepository.save(treatmentPlanToUpdate);
    }


}
