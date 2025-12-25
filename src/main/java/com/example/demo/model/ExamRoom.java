@Entity
@Table(name = "exam_rooms")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="room_number", unique = true, nullable = false)
    private String roomNumber;

    private Integer capacity;

    @Column(name="room_rows")
    private Integer rows;

    @Column(name="room_columns")
    private Integer columns;

    // REQUIRED setters for tests
    public void setId(Long id) { this.id = id; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setRows(Integer rows) { this.rows = rows; }
    public void setColumns(Integer columns) { this.columns = columns; }

    public void ensureCapacityMatches() {
        this.capacity = rows * columns;
    }

    // getters (keep existing)
}
