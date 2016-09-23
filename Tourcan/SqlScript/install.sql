USE [master]
GO

/****** Object:  Database [Tourcan]    Script Date: 2016/7/14 下午 08:53:09 ******/
DROP DATABASE [Tourcan]
GO

/****** Object:  Database [Tourcan]    Script Date: 2016/7/14 下午 08:53:09 ******/
CREATE DATABASE [Tourcan]
GO
ALTER DATABASE [Tourcan] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Tourcan].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Tourcan] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Tourcan] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Tourcan] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Tourcan] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Tourcan] SET ARITHABORT OFF 
GO
ALTER DATABASE [Tourcan] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Tourcan] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [Tourcan] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Tourcan] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Tourcan] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Tourcan] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Tourcan] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Tourcan] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Tourcan] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Tourcan] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Tourcan] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Tourcan] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Tourcan] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Tourcan] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Tourcan] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Tourcan] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Tourcan] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Tourcan] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Tourcan] SET RECOVERY FULL 
GO
ALTER DATABASE [Tourcan] SET  MULTI_USER 
GO
ALTER DATABASE [Tourcan] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Tourcan] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Tourcan] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Tourcan] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Tourcan', N'ON'
GO
USE [Tourcan]
GO
/****** Object:  Table [dbo].[admin]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[admin](
	[admin_name] [nvarchar](20) NOT NULL,
	[admin_account] [varchar](40) NOT NULL,
	[admin_pwd] [varchar](max) NOT NULL,
	[admin_id] [int] IDENTITY(1,1) NOT NULL,
	[admin_level] [int] NOT NULL,
 CONSTRAINT [PK_admin] PRIMARY KEY CLUSTERED 
(
	[admin_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[att]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[att](
	[att_name] [nvarchar](50) NOT NULL,
	[att_id] [int] IDENTITY(1,1) NOT NULL,
	[att_staytime] [int] NOT NULL,
	[region_id] [int] NOT NULL,
	[att_addr] [nvarchar](max) NOT NULL,
	[att_price] [money] NULL,
	[att_phone] [varchar](50) NULL,
	[att_url] [varchar](max) NULL,
	[att_eat] [bit] NOT NULL,
	[att_intro] [nvarchar](max) NULL,
	[att_open] [nvarchar](max) NULL,
	[att_lat] [decimal](8,6) NOT NULL,
	[att_lng] [decimal](9,6) NOT NULL,
 CONSTRAINT [PK_att] PRIMARY KEY CLUSTERED 
(
	[att_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[hotel]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[hotel](
	[hotel_id] [int] IDENTITY(1,1) NOT NULL,
	[region_id] [int] NOT NULL,
	[hotel_name] [nvarchar](20) NOT NULL,
	[hotel_price] [money] NOT NULL,
	[hotel_addr] [nvarchar](max) NOT NULL,
	[hotel_phone] [varchar](50) NULL,
	[hotel_class] [int] NOT NULL,
	[hotel_url] [varchar](max) NULL,
	[hotel_lat] [decimal](8,6) NOT NULL,
	[hotel_lng] [decimal](9,6) NOT NULL,
 CONSTRAINT [PK_hotel] PRIMARY KEY CLUSTERED 
(
	[hotel_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[mem]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[mem](
	[mem_lname] [nvarchar](20) NOT NULL,
	[mem_fname] [nvarchar](20) NOT NULL,
	[mem_nick] [nvarchar](20) NULL,
	[mem_sex] [int] NOT NULL,
	[region_id] [int] NOT NULL,
	[mem_bdate] [date] NOT NULL,
	/*
	[mem_account] [varchar](40) /*NOT*/ NULL,
	[mem_pwd] [varchar](max) /*NOT*/ NULL,
	[mem_id] [int] /*IDENTITY(1,1) NOT*/ NULL,
	*/
	[mem_regtime] [date] NOT NULL,
	[mem_email] [varchar](50) NOT NULL,
	[mem_mobile] [varchar](50) NULL,
	[mem_photo] [varbinary](max) NULL,
	[mem_uid] [varchar](128) NOT NULL,
 CONSTRAINT [PK_mem] PRIMARY KEY CLUSTERED 
(
	[mem_uid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[photo]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[photo](
	[photo_id] [int] IDENTITY(1,1) NOT NULL,
	[photo_file] [varbinary](max) NOT NULL,
	[att_id] [int] NULL,
	[hotel_id] [int] NULL,
 CONSTRAINT [PK_photo] PRIMARY KEY CLUSTERED 
(
	[photo_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[quest]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[quest](
	[quest_catalog] [int] NOT NULL,
	[quest_topic] [nvarchar](20) NOT NULL,
	[quest_id] [int] IDENTITY(1,1) NOT NULL,
	/*[mem_id] [int] NOT NULL,*/
	[mem_uid] [varchar](128) NOT NULL,
	[quest_quiz] [nvarchar](max) NOT NULL,
	[admin_id] [int] NULL,
	[quest_reply] [nvarchar](max) NULL,
	[quest_qtime] [datetime] NOT NULL,
	[quest_rtime] [datetime] NULL,
 CONSTRAINT [PK_quest] PRIMARY KEY CLUSTERED 
(
	[quest_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[region]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[region](
	[region_id] [int] NOT NULL,
	[region_name] [nvarchar](8) NOT NULL,
	[region_area] [int] NOT NULL,
 CONSTRAINT [PK_region] PRIMARY KEY CLUSTERED 
(
	[region_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[resp]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[resp](
	/*[mem_id] [int] NOT NULL,*/
	[mem_uid] [varchar](128) NOT NULL,
	[resp_time] [datetime] NOT NULL,
	[resp_article] [nvarchar](max) NOT NULL,
	[theme_id] [int] NOT NULL,
	[resp_id] [int] IDENTITY(1,1) NOT NULL,
	[resp_topic] [nvarchar](30) NULL,
 CONSTRAINT [PK_resp] PRIMARY KEY CLUSTERED 
(
	[resp_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sex]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sex](
	[sex] [int] NOT NULL,
	[sex_declare] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_sex] PRIMARY KEY CLUSTERED 
(
	[sex] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[theme]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[theme](
	/*[mem_id] [int] NOT NULL,*/
	[mem_uid] [varchar](128) NOT NULL,
	[theme_time] [datetime] NOT NULL,
	[theme_article] [nvarchar](max) NOT NULL,
	[theme_id] [int] IDENTITY(1,1) NOT NULL,
	[theme_topic] [nvarchar](30) NOT NULL,
	[theme_catalog] [int] NOT NULL,
 CONSTRAINT [PK_theme] PRIMARY KEY CLUSTERED 
(
	[theme_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[trip]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[trip](
	[trip_name] [nvarchar](80) NOT NULL,
	[trip_id] [int] IDENTITY(1,1) NOT NULL,
	[trip_ctime] [datetime] NOT NULL,
	[trip_price] [money] NOT NULL,
	/*[mem_id] [int] NOT NULL,*/
	[mem_uid] [varchar](128) NOT NULL,
 CONSTRAINT [PK_trip] PRIMARY KEY CLUSTERED 
(
	[trip_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tripitem]    Script Date: 2016/7/14 下午 08:53:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tripitem](
	[trip_id] [int] NOT NULL,
	[tripitem_serial] [int] NOT NULL,
	[tripitem_id] [int] IDENTITY(1,1) NOT NULL,
	[att_id] [int] NULL,
	[tripitem_traffic] [varchar](max) NULL,
	[hotel_id] [int] NULL,
	[tripitem_memo] [nvarchar](max) NULL,
	[tripitem_staytime] [int] NOT NULL,
	[tripitem_begin] [datetime] NOT NULL,
	[tripitem_end] [datetime] NOT NULL,
	[tripitem_price] [money] NOT NULL,
 CONSTRAINT [PK_tripitem] PRIMARY KEY CLUSTERED 
(
	[tripitem_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[att]  WITH CHECK ADD  CONSTRAINT [FK_att_region] FOREIGN KEY([region_id])
REFERENCES [dbo].[region] ([region_id])
GO
ALTER TABLE [dbo].[att] CHECK CONSTRAINT [FK_att_region]
GO
ALTER TABLE [dbo].[hotel]  WITH CHECK ADD  CONSTRAINT [FK_hotel_region] FOREIGN KEY([region_id])
REFERENCES [dbo].[region] ([region_id])
GO
ALTER TABLE [dbo].[hotel] CHECK CONSTRAINT [FK_hotel_region]
GO
ALTER TABLE [dbo].[mem]  WITH CHECK ADD  CONSTRAINT [FK_mem_region] FOREIGN KEY([region_id])
REFERENCES [dbo].[region] ([region_id])
GO
ALTER TABLE [dbo].[mem] CHECK CONSTRAINT [FK_mem_region]
GO
ALTER TABLE [dbo].[mem]  WITH CHECK ADD  CONSTRAINT [FK_mem_sex] FOREIGN KEY([mem_sex])
REFERENCES [dbo].[sex] ([sex])
GO
ALTER TABLE [dbo].[mem] CHECK CONSTRAINT [FK_mem_sex]
GO
ALTER TABLE [dbo].[photo]  WITH CHECK ADD  CONSTRAINT [FK_photo_att] FOREIGN KEY([att_id])
REFERENCES [dbo].[att] ([att_id])
GO
ALTER TABLE [dbo].[photo] CHECK CONSTRAINT [FK_photo_att]
GO
ALTER TABLE [dbo].[photo]  WITH CHECK ADD  CONSTRAINT [FK_photo_hotel] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[photo] CHECK CONSTRAINT [FK_photo_hotel]
GO
ALTER TABLE [dbo].[quest]  WITH CHECK ADD  CONSTRAINT [FK_quest_admin] FOREIGN KEY([admin_id])
REFERENCES [dbo].[admin] ([admin_id])
GO
ALTER TABLE [dbo].[quest] CHECK CONSTRAINT [FK_quest_admin]
GO
ALTER TABLE [dbo].[quest]  WITH CHECK ADD  CONSTRAINT [FK_quest_mem] FOREIGN KEY([mem_uid])
REFERENCES [dbo].[mem] ([mem_uid])
GO
ALTER TABLE [dbo].[quest] CHECK CONSTRAINT [FK_quest_mem]
GO
ALTER TABLE [dbo].[resp]  WITH CHECK ADD  CONSTRAINT [FK_resp_mem] FOREIGN KEY([mem_uid])
REFERENCES [dbo].[mem] ([mem_uid])
GO
ALTER TABLE [dbo].[resp] CHECK CONSTRAINT [FK_resp_mem]
GO
ALTER TABLE [dbo].[resp]  WITH CHECK ADD  CONSTRAINT [FK_resp_theme] FOREIGN KEY([theme_id])
REFERENCES [dbo].[theme] ([theme_id])
GO
ALTER TABLE [dbo].[resp] CHECK CONSTRAINT [FK_resp_theme]
GO
ALTER TABLE [dbo].[theme]  WITH CHECK ADD  CONSTRAINT [FK_theme_mem] FOREIGN KEY([mem_uid])
REFERENCES [dbo].[mem] ([mem_uid])
GO
ALTER TABLE [dbo].[theme] CHECK CONSTRAINT [FK_theme_mem]
GO
ALTER TABLE [dbo].[trip]  WITH CHECK ADD  CONSTRAINT [FK_trip_mem1] FOREIGN KEY([mem_uid])
REFERENCES [dbo].[mem] ([mem_uid])
GO
ALTER TABLE [dbo].[trip] CHECK CONSTRAINT [FK_trip_mem1]
GO
ALTER TABLE [dbo].[tripitem]  WITH CHECK ADD  CONSTRAINT [FK_tripitem_att] FOREIGN KEY([att_id])
REFERENCES [dbo].[att] ([att_id])
GO
ALTER TABLE [dbo].[tripitem] CHECK CONSTRAINT [FK_tripitem_att]
GO
ALTER TABLE [dbo].[tripitem]  WITH CHECK ADD  CONSTRAINT [FK_tripitem_hotel] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[tripitem] CHECK CONSTRAINT [FK_tripitem_hotel]
GO
ALTER TABLE [dbo].[tripitem]  WITH CHECK ADD  CONSTRAINT [FK_tripitem_trip] FOREIGN KEY([trip_id])
REFERENCES [dbo].[trip] ([trip_id])
GO
ALTER TABLE [dbo].[tripitem] CHECK CONSTRAINT [FK_tripitem_trip]
GO
USE [master]
GO
ALTER DATABASE [Tourcan] SET  READ_WRITE 
GO
