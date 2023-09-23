package com.io.ms.entities.school;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school_meetings")
public class SchoolMBPMeetingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meetingDateTime", nullable = false, length = 60)
    private LocalDateTime meetingDateTime;

    @Column(name = "nextAppointment", nullable = false, length = 60)
    private LocalDateTime nextAppointment;

    @Column(name = "mom", length = 300)
    private String mom;

    @Column(name = "feedback_Whatwentwell", length = 100)
    private String feedback_Whatwentwell;

    @Column(name = "feedback_Improvement", length = 100)
    private String feedback_Improvement;

    @CreationTimestamp
    @Column(name = "createdAt",updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updatedAt",insertable = false)
    private LocalDate updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolNameRequest_id")
    public SchoolNameRequest schoolNmReq;
}
