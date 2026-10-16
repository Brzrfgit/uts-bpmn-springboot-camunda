package com.example.utssoal23;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service Task: Dijalankan otomatis ketika Dosen Pembimbing menolak/meminta revisi proposal (disetujui == false).
 */
@Component("proposalDitolakDelegate")
public class ProposalDitolakDelegate implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(ProposalDitolakDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String nim = (String) execution.getVariable("nim");
        String nama = (String) execution.getVariable("namaMahasiswa");
        String judul = (String) execution.getVariable("judulProposal");
        String pembimbing = (String) execution.getVariable("dosenPembimbing");
        String catatan = (String) execution.getVariable("catatanDosen");

        String tanggalKeputusan = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        // Simpan hasil ke variabel proses Camunda
        execution.setVariable("statusProposal", "PERLU_REVISI");
        execution.setVariable("tanggalKeputusan", tanggalKeputusan);

        logger.warn("==================================================================");
        logger.warn("⚠️ [SERVICE TASK: PROPOSAL PERLU REVISI / DITOLAK]");
        logger.warn("📌 NIM Mahasiswa    : {}", nim);
        logger.warn("📌 Nama Mahasiswa   : {}", nama);
        logger.warn("📌 Judul Proposal   : {}", judul);
        logger.warn("📌 Pembimbing       : {}", pembimbing);
        logger.warn("📌 Catatan Dosen    : {}", catatan);
        logger.warn("📌 Status Akhir     : DITOLAK / PERLU REVISI - Silakan perbaiki proposal!");
        logger.warn("==================================================================");
    }
}
