using System.ComponentModel.DataAnnotations;

namespace MvcNews.Models
{
    public class NewsItem
    {
        [Key]
        public int Id { get; set; }
        [DataType(DataType.DateTime)]
        public DateTime TimeStamp { get; set; }
        [Required]
        [StringLength(140, MinimumLength = 5)]
        public string Text { get; set; }

        [Timestamp]
        public byte[]? RowVersion { get; set; }
    }
}
