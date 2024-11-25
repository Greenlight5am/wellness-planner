package com.prova.wellnessplanner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "t_media")
public class Media {

    private Integer id;
    private String fileName;
    private String fileType;
    private byte[] data;
    private Activity activity;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Column(name = "file_name", nullable = false)
    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    @Column(name = "file_type", nullable = false)
    public String getFileType() { return fileType; }

    public void setFileType(String fileType) { this.fileType = fileType; }

    @Lob
    @Column(name = "data", nullable = false)
    public byte[] getData() { return data; }

    public void setData(byte[] data) { this.data = data; }

    @OneToOne(mappedBy = "media")
    public Activity getActivity() { return activity; }

    public void setActivity(Activity activity) { this.activity = activity; }
}
