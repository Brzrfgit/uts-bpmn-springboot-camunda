package com.example.utssoal23;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Service Task: Dijalankan otomatis ketika Dosen Pembimbing menyetujui proposal (disetujui == true).
 */
@Component("proposalDisetujuiDelegate")
public class ProposalDisetujuiDelegate implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(ProposalDisetujuiDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String nim = (String) execution.getVariable("nim");
        String nama = (String) execution.getVariable("namaMahasiswa");
        String judul = (String) execution.getVariable("judulProposal");
        String pembimbing = (String) execution.getVariable("dosenPembimbing");
        String catatan = (String) execution.getVariable("catatanDosen");

        // Generate nomor registrasi proposal resmi otomatis
        String noRegistrasi = "SKRIPS-UIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String tanggalDisetujui = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        // Simpan hasil ke variabel proses Camunda
        execution.setVariable("noRegistrasi", noRegistrasi);
        execution.setVariable("statusProposal", "DISETUJUI");
        execution.setVariable("tanggalKeputusan", tanggalDisetujui);

        logger.info("==================================================================");
        logger.info("🎓 [SERVICE TASK: PROPOSAL DISETUJUI]");
        logger.info("📌 Nomor Registrasi : {}", noRegistrasi);
        logger.info("📌 NIM Mahasiswa    : {}", nim);
        logger.info("📌 Nama Mahasiswa   : {}", nama);
        logger.info("📌 Judul Proposal   : {}", judul);
        logger.info("📌 Pembimbing       : {}", pembimbing);
        logger.info("📌 Catatan Dosen    : {}", catatan);
        logger.info("📌 Status Akhir     : DISETUJUI - Silakan melanjutkan bimbingan Bab 1!");
        logger.info("==================================================================");
    }
}
